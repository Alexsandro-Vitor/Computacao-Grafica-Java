package geometria3D;

import auxiliar.*;
import luz.Cor;

/**
 * Classe que representa um tri�ngulo em tr�s dimens�es.
 * @since v0.2.0
 * @author Alexsandro V�tor Serafim de Carvalho - avsc@cin.ufpe.br
 */
public class Triangulo3D {
	/**
	 * V�rtice A do tri�ngulo.
	 */
	public Vetor3D a;
	
	/**
	 * V�rtice B do tri�ngulo.
	 */
	public Vetor3D b;
	
	/**
	 * V�rtice C do tri�ngulo.
	 */
	public Vetor3D c;
	
	/**
	 * Cor do triangulo
	 * @since v0.3.0
	 */
	public Cor cor;
	
	/**
	 * Constr�i um tri�ngulo com cor a partir dos seus v�rtices. Se a cor for null, a tri�ngulo ser� branco
	 * @param a V�rtice A do tri�ngulo
	 * @param b V�rtice B do tri�ngulo
	 * @param c V�rtice C do tri�ngulo
	 * @param Cor A cor do tri�ngulo
	 * @since v0.3.0
	 */
	public Triangulo3D(Vetor3D a, Vetor3D b, Vetor3D c, Cor cor) {
		this.a = a;
		this.b = b;
		this.c = c;
		this.cor = (cor != null) ? cor : Cor.BRANCO;
	}
	
	/**
	 * Retorna o vetor normal do tri�ngulo. Esse vetor � perpendicular ao plano no qual o tri�ngulo est� contido.
	 * @return O vetor normal do tri�ngulo normalizado
	 */
	Vetor3D normal() {
		Vetor3D ab = a.sub(b);
		Vetor3D ac = a.sub(c);
		return ab.prodVetorial(ac).normalizado();
	}
	
	/**
	 * Aplica uma transla��o na dire��o do vetor passado como par�metro.
	 * @param v A dire��o da transla��o
	 * @return Um novo tri�ngulo com os v�rtices nas novas posi��es
	 */
	public Triangulo3D translacao(Vetor3D v) {
		return new Triangulo3D(a.sub(v), b.sub(v), c.sub(v), cor);
	}
	
	/**
	 * Checa se um vetor atravessa o tri�ngulo.
	 * @param v O vetor que atravessa ou n�o o tri�ngulo
	 * @return O ponto em que o vetor e o tri�ngulo se cruzam, caso n�o exista, retorna null
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
	 * Checa se o ponto p est� contido no tri�ngulo.
	 * @param p O ponto a ser checado
	 * @return true, se p estiver contido; false se n�o estiver
	 */
	private boolean contido(Vetor3D p) {
		Vetor3D[] solucao = Linha6.solucionar(new Linha6(a, 1, 0, 0), new Linha6(b, 0, 1, 0), new Linha6(c, 0, 0, 1));
		Vetor3D pesos = solucao[0].mult(p.x).soma(solucao[1].mult(p.y).soma(solucao[2].mult(p.z)));
		return de0a1(pesos);
	}
	
	/**
	 * Checa se as dimens�es x, y, e z do Vetor3D est�o contidas no intervalo [0, 1].
	 * @param v O Vetor3D que ser� analisado
	 * @return true, se as 3 dimens�es estiverem dentro do intervalo;
	 * false, se uma delas n�o estiver no intervalo
	 */
	private static boolean de0a1(Vetor3D v) {
		return de0a1(v.x) && de0a1(v.y) && de0a1(v.z);
	}
	
	/**
	 * Checa se um valor est� contido no intervalo [0, 1].
	 * @param i O valor a ser analisado 
	 * @return true se i estiver contido em [0, 1]; false, se n�o estiver
	 */
	private static boolean de0a1(double i) {
		return 0 <= i && i <= 1;
	}
}
