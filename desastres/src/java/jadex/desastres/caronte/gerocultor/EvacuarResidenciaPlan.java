package jadex.desastres.caronte.gerocultor;

import jadex.desastres.*;
import org.json.me.*;

/**
 * Plan de GEROCULTOR
 *
 * @author Lorena Lopez Lebon y Juan Luis Molina
 */
public class EvacuarResidenciaPlan extends EnviarMensajePlan{

	Environment env;

	public void body(){
		env = (Environment)getBeliefbase().getBelief("env").getFact();
		Position posResi = (Position)getBeliefbase().getBelief("residencia").getFact();

		int idDes = (Integer)getBeliefbase().getBelief("idEmergencia").getFact();
		Disaster des = env.getEvent(idDes);
		double dif = 0.0006;

		env.printout("GG gerocultor: evacuando la residencia", 0);

		try{
			String sanosAux = Connection.connect(Environment.URL + "healthy");
			JSONArray sanos = new JSONArray(sanosAux);
			String levesAux = Connection.connect(Environment.URL + "slight");
			JSONArray leves = new JSONArray(levesAux);

			int numeroEvacuadosSanos = Math.round(2*sanos.length()/3);
			env.printout("GG gerocultor: de " + sanos.length() + " residentes sanos me encargo de evacuar a " + numeroEvacuadosSanos, 0);
			// del resto de sanos se encarga el auxiliar

			for(int i = 0; i < numeroEvacuadosSanos; i++){
				JSONObject sano = sanos.getJSONObject(i);
				evacuar(sano, 0);
			}

			env.printout("GG gerocultor: todos los residentes a cargo del gerocultor evacuados!!", 0);

			String recibido = esperarYEnviarRespuesta("fin_emergencia", "Fin recibido");
			env.printout("GG gerocultor: llevo a los residentes de vuelta", 0);

			for(int i = 0; i < leves.length(); i++){
				JSONObject leve = leves.getJSONObject(i);
				evacuar(leve, 1);
			}

			for(int i = 0; i < sanos.length(); i++){
				JSONObject sano = sanos.getJSONObject(i);
				evacuar(sano, 1);
			}
		}catch(Exception ex){
			System.out.println("Excepcion en EvacuarResidenciaPlan: " + ex);
		}

		// Vuelve a su posicion de la residencia
		try{
			env.andar(getComponentName(), (Position)getBeliefbase().getBelief("pos").getFact(), posResi, env.getAgent(getComponentName()).getId(), 0);
		}catch (InterruptedException ex){
			System.out.println("Error al andar: " + ex);
		}

		env.printout("GG gerocultor: todos los residentes de vuelta en la residencia!!", 0);
	}

	/**
	 *
	 * @param persona Persona a evacuar
	 * @param dir Accion: 0=sacar, 1=meter
	 */
	private void evacuar(JSONObject persona, int dir){
		double dif1 = 0.0;
		double dif2 = 0.0006;
		String msg = "evacuando al anciano";
		if(dir == 1){
			dif1 = 0.0006;
			dif2 = 0.0;
			msg = "llevando de vuelta al anciano";
		}
		try{
			int id = persona.getInt("id");
			Position posSano1 = new Position(new Double(persona.getString("latitud")), new Double(persona.getString("longitud")) - dif1);
			Position posSano2 = new Position(new Double(persona.getString("latitud")), new Double(persona.getString("longitud")) - dif2);
			Position posAnt = (Position) getBeliefbase().getBelief("pos").getFact();
			env.andar(getComponentName(), posAnt, posSano1, env.getAgent(getComponentName()).getId(), 0);
			env.printout("GG gerocultor: " + msg + " " + id, 0);
			env.andar(getComponentName(), posSano1, posSano2, env.getAgent(getComponentName()).getId(), id);
		}catch (Exception ex){
			System.out.println("Excepcion en evacuar: " + ex);
		}
	}
}