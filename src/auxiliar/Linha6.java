package auxiliar;

import geometria3D.Vetor3D;

public class Linha6 {
	public double a, b, c, d, e, f;

	public Linha6(Vetor3D vetor, double d, double e, double f) {
		this.a = vetor.x;
		this.b = vetor.y;
		this.c = vetor.z;
		this.d = d;
		this.e = e;
		this.f = f;
	}
	
	public Linha6(double a, double b, double c, double d, double e, double f) {
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
		this.e = e;
		this.f = f;
	}

	public Linha6 somaA(Linha6 l) {
		return new Linha6(a - l.a * a, b - l.b * a, c - l.c * a, d - l.d * a, e - l.e * a, f - l.f * a);
	}

	public Linha6 somaB(Linha6 l) {
		return new Linha6(a, b - l.b * b, c - l.c * b, d - l.d * b, e - l.e * b, f - l.f * b);
	}

	public Linha6 somaC(Linha6 l) {
		return new Linha6(a, b, c - l.c * c, d - l.d * c, e - l.e * c, f - l.f * c);
	}

	public Linha6 div(double i) {
		return new Linha6(a / i, b / i, c / i, d / i, e / i, f / i);
	}

	public static Vetor3D[] solucionar(Linha6 l1, Linha6 l2, Linha6 l3) {
		while (l1.a == 0) {
			Linha6 temp = l1;
			l1 = l2;
			l2 = l3;
			l3 = temp;
		}
		l1 = l1.div(l1.a);
		l2 = l2.somaA(l1);
		l3 = l3.somaA(l1);
		while (l2.b == 0) {
			Linha6 temp = l2;
			l2 = l3;
			l3 = temp;
		}
		l2 = l2.div(l2.b);
		l1 = l1.somaB(l2);
		l3 = l3.somaB(l2);
		l3 = l3.div(l3.c);
		l1 = l1.somaC(l3);
		l2 = l2.somaC(l3);
		Vetor3D[] saida = {vetor(l1), vetor(l2), vetor(l3)};
		return saida;
	}
		
	public static Vetor3D vetor(Linha6 l) {
		return new Vetor3D(l.d, l.e, l.f);
	}
}
