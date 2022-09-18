package py.una.entidad;

public class Mensaje {
	Long tipoMensaje;
	Long cedula;
	String nombre;
	String apellido;
    String chapa;
	String marca;
    Long monto;

    public Mensaje(){ }

    public Mensaje(Long mtipoMensaje){
        this.tipoMensaje = mtipoMensaje;
    }
    
    public Mensaje(Long mtipoMensaje, Long mcedula, String mnombre, String mapellido, String mchapa, String mmarca, Long mmonto){
        this.tipoMensaje = mtipoMensaje;
        this.cedula = mcedula;
        this.nombre = mnombre;
        this.apellido = mapellido;
        this.chapa = mchapa;
        this.marca = mmarca;
        this.monto = mmonto;
    }

	public Long getTipoMensaje() {
		return tipoMensaje;
	}

	public void setTipoMensaje(Long tipoMensaje) {
		this.tipoMensaje = tipoMensaje;
	}

	public Long getCedula() {
		return cedula;
	}

	public void setCedula(Long cedula) {
		this.cedula = cedula;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getChapa() {
		return chapa;
	}

	public void setChapa(String chapa) {
		this.chapa = chapa;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public Long getMonto() {
		return monto;
	}

	public void setMonto(Long monto) {
		this.monto = monto;
	}
}
