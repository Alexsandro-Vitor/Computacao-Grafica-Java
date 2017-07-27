package geometria3D;

import luz.Cor;

/**
 * Classe abstrata que representa formas em três dimensões.
 * @since v0.4.0
 * @author Alexsandro Vítor Serafim de Carvalho - avsc@cin.ufpe.br
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
	 * Aplica uma translação na direção do vetor passado como parâmetro.
	 * @param v A direção da translação
	 * @return Uma nova movida para sua nova posição
	 */
	public abstract Forma3D translacao(Vetor3D v);
	
	/**
	 * Checa se um vetor atravessa a forma.
	 * @param v O vetor que atravessa ou não a forma
	 * @return O ponto em que o vetor e a forma se cruzam, caso não exista, retorna null
	 */
	public abstract Vetor3D colisao(Vetor3D v);
}
