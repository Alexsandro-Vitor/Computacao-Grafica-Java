package geometria3D;

import auxiliar.*;
import luz.Cor;

/**
 * Classe que representa um triângulo em três dimensões.
 * @since v0.2.0
 * @author Alexsandro Vítor Serafim de Carvalho - avsc@cin.ufpe.br
 */
public class Triangulo3D {
	/**
	 * Vértice A do triângulo.
	 */
	public Vetor3D a;
	
	/**
	 * Vértice B do triângulo.
	 */
	public Vetor3D b;
	
	/**
	 * Vértice C do triângulo.
	 */
	public Vetor3D c;
	
	/**
	 * Cor do triangulo
	 * @since v0.3.0
	 */
	public Cor cor;
	
	/**
	 * Constrói um triângulo com cor a partir dos seus vértices. Se a cor for null, a triângulo será branco
	 * @param a Vértice A do triângulo
	 * @param b Vértice B do triângulo
	 * @param c Vértice C do triângulo
	 * @param Cor A cor do triângulo
	 * @since v0.3.0
	 */
	public Triangulo3D(Vetor3D a, Vetor3D b, Vetor3D c, Cor cor) {
		this.a = a;
		this.b = b;
		this.c = c;
		this.cor = (cor != null) ? cor : Cor.BRANCO;
	}
	
	/**
	 * Retorna o vetor normal do triângulo. Esse vetor é perpendicular ao plano no qual o triângulo está contido.
	 * @return O vetor normal do triângulo normalizado
	 */
	Vetor3D normal() {
		Vetor3D ab = a.sub(b);
		Vetor3D ac = a.sub(c);
		return ab.prodVetorial(ac).normalizado();
	}
	
	/**
	 * Aplica uma translação na direção do vetor passado como parâmetro.
	 * @param v A direção da translação
	 * @return Um novo triângulo com os vértices nas novas posições
	 */
	public Triangulo3D translacao(Vetor3D v) {
		return new Triangulo3D(a.sub(v), b.sub(v), c.sub(v), cor);
	}
	
	/**
	 * Checa se um vetor atravessa o triângulo.
	 * @param v O vetor que atravessa ou não o triângulo
	 * @return O ponto em que o vetor e o triângulo se cruzam, caso não exista, retorna null
	 */
	public Vetor3D colisao(Vetor3D v) {
		Vetor3D normal = normal();
		Linha4 plano = new Linha4(normal, a);
		Linha4 retaA = new Linha4(0, v.z, -v.y, 0);
		Linha4 retaB = new Linha4(v.z, 0, -v.x, 0);
		Vetor3D ponto = Linha4.solucionar(plano, retaA, retaB);
		return contido(ponto) ? ponto : null;
	}
	
	/**
	 * Checa se o ponto p está contido no triângulo.
	 * @param p O ponto a ser checado
	 * @return true, se p estiver contido; false se não estiver
	 */
	private boolean contido(Vetor3D p) {
		Vetor3D[] solucao = Linha6.solucionar(new Linha6(a, 1, 0, 0), new Linha6(b, 0, 1, 0), new Linha6(c, 0, 0, 1));
		Vetor3D pesos = solucao[0].mult(p.x).soma(solucao[1].mult(p.y).soma(solucao[2].mult(p.z)));
		return de0a1(pesos);
	}
	
	/**
	 * Checa se as dimensões x, y, e z do Vetor3D estão contidas no intervalo [0, 1].
	 * @param v O Vetor3D que será analisado
	 * @return true, se as 3 dimensões estiverem dentro do intervalo;
	 * false, se uma delas não estiver no intervalo
	 */
	private static boolean de0a1(Vetor3D v) {
		return de0a1(v.x) && de0a1(v.y) && de0a1(v.z);
	}
	
	/**
	 * Checa se um valor está contido no intervalo [0, 1].
	 * @param i O valor a ser analisado 
	 * @return true se i estiver contido em [0, 1]; false, se não estiver
	 */
	private static boolean de0a1(double i) {
		return 0 <= i && i <= 1;
	}
}
