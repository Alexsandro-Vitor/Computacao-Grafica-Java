package geometria3D;

import luz.Cor;

/**
 * Classe abstrata que representa formas em tr�s dimens�es.
 * @since v0.4.0
 * @author Alexsandro V�tor Serafim de Carvalho - avsc@cin.ufpe.br
 */
public abstract class Forma3D {
	/**
	 * A cor da forma.
	 */
	public abstract Cor getCor();
	
	/**
	 * Retorna o vetor normal da forma. Cada forma a implementa a seu modo.
	 * @return O vetor normal da forma normalizado
	 */
	public abstract Vetor3D normal(Vetor3D v);
	
	/**
	 * Aplica uma transla��o na dire��o do vetor passado como par�metro.
	 * @param v A dire��o da transla��o
	 * @return Uma nova movida para sua nova posi��o
	 */
	public abstract Forma3D translacao(Vetor3D v);
	
	/**
	 * Checa se um vetor atravessa a forma.
	 * @param v O vetor que atravessa ou n�o a forma
	 * @return O ponto em que o vetor e a forma se cruzam, caso n�o exista, retorna null
	 */
	public abstract Vetor3D colisao(Vetor3D v);
}
