package auxiliar;

import geometria3D.Vetor3D;

public class Linha4 {
	public double a, b, c, d;

	public Linha4(Vetor3D vetor, Vetor3D ponto) {
		this.a = vetor.x;
		this.b = vetor.y;
		this.c = vetor.z;
		this.d = vetor.prodEscalar(ponto);
	}
	
	public Linha4(double a, double b, double c, double d) {
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
	}

	public Linha4 somaA(Linha4 l) {
		return new Linha4(a - l.a * a, b - l.b * a, c - l.c * a, d - l.d * a);
	}

	public Linha4 somaB(Linha4 l) {
		return new Linha4(a, b - l.b * b, c - l.c * b, d - l.d * b);
	}

	public Linha4 somaC(Linha4 l) {
		return new Linha4(a, b, c - l.c * c, d - l.d * c);
	}

	public Linha4 div(double i) {
		return new Linha4(a / i, b / i, c / i, d / i);
	}

	public static Vetor3D solucionar(Linha4 l1, Linha4 l2, Linha4 l3) {
		while (l1.a == 0) {
			Linha4 temp = l1;
			l1 = l2;
			l2 = l3;
			l3 = temp;
		}
		l1 = l1.div(l1.a);
		l2 = l2.somaA(l1);
		l3 = l3.somaA(l1);
		if (l2.b == 0) {
			Linha4 temp = l2;
			l2 = l3;
			l3 = temp;
		}
		l2 = l2.div(l2.b);
		l1 = l1.somaB(l2);
		l3 = l3.somaB(l2);
		l3 = l3.div(l3.c);
		l1 = l1.somaC(l3);
		l2 = l2.somaC(l3);
		return new Vetor3D(l1.d, l2.d, l3.d);
	}
}
