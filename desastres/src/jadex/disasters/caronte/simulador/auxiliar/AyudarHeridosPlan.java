package disasters.caronte.simulador.auxiliar;

import disasters.*;
import disasters.caronte.Entorno;
import disasters.caronte.simulador.ontology.Desastre;
import jadex.bdi.runtime.IGoal;

/**
 * Plan de AUXILIAR.
 * 
 * @author Juan Luis Molina Nogales
 */
public class AyudarHeridosPlan extends EnviarMensajePlan{

	/**
	 * Cuerpo del plan.
	 */
	public void body(){
		// Obtenemos un objeto de la clase Entorno para poder usar sus metodos
		Entorno env = (Entorno)getBeliefbase().getBelief("env").getFact();

		Desastre recibido = (Desastre)enviarRespuestaObjeto("ack_aviso_geriatrico", "Aviso recibido");
		//env.printout("XX auxiliar: Ack mandado", 0);

		// Posicion de la residencia que le corresponde
		Position posResi = (Position)getBeliefbase().getBelief("residencia").getFact();
		Position posicion = (Position)getBeliefbase().getBelief("pos").getFact();

		int idDes = recibido.getId();
		getBeliefbase().getBelief("idEmergencia").setFact(idDes);
		Disaster des = env.getEvent(idDes);
		Position positionDesastre = new Position(des.getLatitud(), des.getLongitud());

		try{
			env.andar(getComponentName(), posicion, positionDesastre, env.getAgent(getComponentName()).getId(), 0);
		}catch(Exception ex){
			System.out.println("Error al andar: " + ex);
		}
		
		env.printout("XX auxiliar: Estoy atendiendo la emergencia: " + idDes, 2, 0, true);

		People herido = getHerido(des);
		String desSize = des.getSize();

		// Heridos
		if(herido != null && !des.getType().equals("injuredPerson") && (desSize.equals("big") || desSize.equals("huge"))){
			IGoal evacuarHeridos = createGoal("evacuarHeridos");
			dispatchSubgoalAndWait(evacuarHeridos);
		}else{
			env.printout("XX auxiliar: emergencia sin heridos...", 2, 0, true);
		}

		if(!des.getType().equals("injuredPerson") && (desSize.equals("big") || desSize.equals("huge"))){
			IGoal colaborarEvacuarSanos = createGoal("colaborarEvacuarSanos");
			dispatchSubgoalAndWait(colaborarEvacuarSanos);
		}

		String recibido2 = esperarYEnviarRespuesta("fin_emergencia", "Fin recibido");

		if(!des.getType().equals("injuredPerson") && (desSize.equals("big") || desSize.equals("huge"))){
			// Vuelve a su posicion de la residencia
			try{
				env.printout("XX auxiliar: vuelvo a la residencia", 2, 0, true);
				env.andar(getComponentName(), (Position) getBeliefbase().getBelief("pos").getFact(), posResi, env.getAgent(getComponentName()).getId(), 0);
			}catch(Exception ex){
				System.out.println("Error al andar: " + ex);
			}
		}
	}

	/**
	 * Coge un herido.
	 * 
	 * @param des desastre
	 * @return herido
	 */
	private People getHerido(Disaster des){
		People herido = null;

		if(des.getSlight() != null){
			herido = des.getSlight();
		}
		if(des.getSerious() != null){
			herido = des.getSerious();
		}
		if(des.getDead() != null){
			herido = des.getDead();
		}

		return herido;
	}
}