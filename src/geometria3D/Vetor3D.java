package geometria3D;

public class Vetor3D {
	public static final Vetor3D ZERO = new Vetor3D(0, 0, 0);
	
	public double x, y, z;
	
	public Vetor3D(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Vetor3D soma(Vetor3D v) {
		return new Vetor3D(x + v.x, y + v.y, z + v.z);
	}
	
	public Vetor3D sub(Vetor3D v) {
		return new Vetor3D(x - v.x, y - v.y, z - v.z);
	}
	
	public Vetor3D mult(double i) {
		return new Vetor3D(x * i, y * i, z * i);
	}
	
	//Produto escalar
	public double prodEscalar(Vetor3D v) {
		return x * v.x + y * v.y + z * v.z;
	}
	
	//Produto vetorial
	public Vetor3D prodVetorial(Vetor3D v) {
		return new Vetor3D(y * v.z - z * v.y, z * v.x - x * v.z, x * v.y - y * v.x);
	}
	
	public Vetor3D div(double i) {
		return new Vetor3D(x / i, y / i, z / i);
	}
	
	public Vetor3D projetarSobre(Vetor3D v) {
		return v.mult(prodEscalar(v) / v.prodEscalar(v));
	}
	
	public double modulo() {
		return Math.sqrt(x * x + y * y + z * z);
	}
	
	public Vetor3D normalizado() {
		return (modulo() != 0) ? div(modulo()) : Vetor3D.ZERO;
	}
}
