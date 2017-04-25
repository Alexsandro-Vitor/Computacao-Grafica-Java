package geometria3D;

import auxiliar.*;

public class Triangulo3D {
	public Vetor3D a, b, c;
	
	public Triangulo3D(Vetor3D a, Vetor3D b, Vetor3D c) {
		this.a = a;
		this.b = b;
		this.c = c;
	}
	
	public Vetor3D normal() {
		Vetor3D ab = a.sub(b);
		Vetor3D ac = a.sub(c);
		return ab.prodVetorial(ac).normalizado();
	}
	
	public Triangulo3D translacao(Vetor3D v) {
		return new Triangulo3D(a.sub(v), b.sub(v), c.sub(v));
	}
	
	public Vetor3D colisao(Vetor3D v, boolean print) {
		Vetor3D normal = normal();
		normal = Vetor3D.ZERO.sub(normal);
		Linha4 plano = new Linha4(normal, a);
		Linha4 retaA = new Linha4(0, v.z, -v.y, 0);
		Linha4 retaB = new Linha4(v.z, 0, -v.x, 0);
		Vetor3D ponto = Linha4.solucionar(plano, retaA, retaB);
		//if (print) System.out.println("Ponto"+ponto.x+", "+ponto.y+", "+ponto.z);
		return contido(ponto, print) ? ponto : null;
	}
	
	private boolean contido(Vetor3D p, boolean print) {
		Vetor3D[] solucao = Linha6.solucionar(new Linha6(a, 1, 0, 0), new Linha6(b, 0, 1, 0), new Linha6(c, 0, 0, 1));
		//if (print) System.out.println("Solucao[0] = "+solucao[0].x+", "+solucao[0].y+", "+solucao[0].z);
		//if (print) System.out.println("Solucao[1] = "+solucao[1].x+", "+solucao[1].y+", "+solucao[1].z);
		//if (print) System.out.println("Solucao[2] = "+solucao[2].x+", "+solucao[2].y+", "+solucao[2].z);
		Vetor3D pesos = solucao[0].mult(p.x).soma(solucao[1].mult(p.y).soma(solucao[2].mult(p.z)));
		//if (print) System.out.println("Pesos"+pesos.x+", "+pesos.y+", "+pesos.z);
		return Metodos.de0a1(pesos);
	}
}
