package luz;

import geometria3D.Vetor3D;

/**
 * Classe que representa um conjunto de fontes de luz.
 * @since v0.3.0
 * @author Alexsandro Vítor Serafim de Carvalho - avsc@cin.ufpe.br
 */
public class Luzes {
	/**
	 * Cor da luz ambiente.
	 */
	private Cor ambiente;
	
	/**
	 * Luzes pontuais do conjunto de luzes.
	 */
	private LuzPonto[] pontuais;
	
	/**
	 * Construtor de Luzes, recebe uma cor para a luz ambiente
	 * @param ambiente A cor da luz ambiente, se receber null será cor preta
	 * @param pontuais Um array de luzes pontuais
	 */
	public Luzes(Cor ambiente, LuzPonto[] pontuais) {
		this.ambiente = (ambiente != null) ? ambiente : Cor.PRETO;
		this.pontuais = (pontuais != null) ? pontuais : new LuzPonto[0];
	}
	
	/**
	 * Ilumina um ponto de uma superfície colorida com a luz ambiente e as luzes pontuais.
	 * @param ponto O ponto a ser colorido
	 * @param normal A normal da superfície no ponto
	 * @param pintura A cor da superfície
	 * @return A cor do ponto colorido
	 */
	public Cor iluminar(Vetor3D ponto, Vetor3D normal, Cor pintura) {
		Cor cor = ambiente;
		for (int i = 0; i < pontuais.length; i++) {
			cor = Cor.soma(cor, pontuais[i].iluminar(ponto, normal));
		}
		return Cor.filtro(cor, pintura);
	}
}
