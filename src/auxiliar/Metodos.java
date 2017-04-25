package auxiliar;

import geometria3D.Vetor3D;

public class Metodos {
	/*public static boolean eq(double a, double b) {
		return abs(a - b) < 10e-3;
	}
	
	public static double abs(double i) {
		return (i > 0) ? i : -i;
	}
	*/
	public static boolean de0a1(Vetor3D v) {
		return de0a1(v.x) && de0a1(v.y) && de0a1(v.z);
	}
	
	public static boolean de0a1(double i) {
		return 0 <= i && i <= 1;
	}
}
