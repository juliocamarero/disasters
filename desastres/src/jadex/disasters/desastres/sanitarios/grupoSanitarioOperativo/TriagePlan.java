package disasters.desastres.sanitarios.grupoSanitarioOperativo;

import disasters.*;
import disasters.desastres.Environment;
import jadex.bdi.runtime.IGoal;

/**
 * Es fundamental la primera evaluaci&oacute;n de la gravedad por parte de
 * m&eacute;dicos o personal sanitario avanzado.
 * 
 * @author Juan Luis Molina Nogales
 */
public class TriagePlan extends EnviarMensajePlan{
	
	/**
	 * Cuerpo del plan.
	 */
	public void body(){
		// Obtenemos un objeto de la clase Environment para poder usar sus metodos
		Environment env = (Environment)getBeliefbase().getBelief("env").getFact();

		enviarRespuesta("ack_aviso_coor", "Aviso recibido");
		System.out.println("** Grupo sanitario: Ack mandado");

		int idDes = env.getTablon();
		Disaster des = env.getEvent(idDes);

		// Sacamos el herido
		People herido = null;
		if (des.getSlight() != null){
			herido = des.getSlight();
		}else if (des.getSerious() != null){
			herido = des.getSerious();
		}else if (des.getDead() != null){
			herido = des.getDead();
		}
		
		if(herido != null){
			System.out.println("** Grupo sanitario: herido " + herido.getId() + " con estado " + herido.getType());

			if(herido.getType().equals("serious")){
				IGoal primerosAuxilios = createGoal("primerosAuxilios");
				dispatchSubgoalAndWait(primerosAuxilios);
			}else{
				enviarMensaje("medicoCACH", "triage", "go", true);
			}
		}else{
			System.out.println("** Grupo sanitario: desastre sin heridos");
			enviarMensaje("medicoCACH", "triage", "go", true);
		}
	}
}