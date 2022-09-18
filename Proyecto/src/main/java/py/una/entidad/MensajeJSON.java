package py.una.entidad;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class MensajeJSON {
    public static String objetoString(Mensaje m) {	
    	
		JSONObject obj = new JSONObject();
        obj.put("tipoMensaje", m.getTipoMensaje());
        obj.put("cedula", m.getCedula());
        obj.put("nombre", m.getNombre());
        obj.put("apellido", m.getApellido());
        obj.put("chapa", m.getChapa());
        obj.put("marca", m.getMarca());
        obj.put("monto", m.getMonto());

        return obj.toJSONString();
    }

    public static Mensaje stringObjeto(String str) throws Exception {
    	Mensaje m = new Mensaje();
        JSONParser parser = new JSONParser();

        Object obj = parser.parse(str.trim());
        JSONObject jsonObject = (JSONObject) obj;

        Long cedula = (Long) jsonObject.get("cedula");
        Long monto = (Long) jsonObject.get("monto");
        Long tipoMensaje = (Long) jsonObject.get("tipoMensaje");
        m.setTipoMensaje(tipoMensaje);
        m.setCedula(cedula);
        m.setNombre((String)jsonObject.get("nombre"));
        m.setApellido((String)jsonObject.get("apellido"));
        m.setChapa((String)jsonObject.get("chapa"));
        m.setMarca((String)jsonObject.get("marca"));
        m.setMonto(monto);
        
        return m;
	}


}


