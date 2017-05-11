package luz;

import geometria3D.Vetor3D;

/**
 * Classe que representa uma fonte pontual de luz.
 * @since v0.3.0
 * @author Alexsandro Vítor Serafim de Carvalho - avsc@cin.ufpe.br
 */
public class LuzPonto {
	/**
	 * Posição da fonte de luz
	 */
	private Vetor3D pos;
	
	/**
	 * Cor da luz emitida
	 */
	private Cor corLuz;
	
	private double kDifuso;
	
	/**
	 * Construtor de LuzPonto. Recebe a posição da luz, sua cor e seu coeficiente de difusão.
	 * @param pos A posição da luz
	 * @param corLuz A cor da luz
	 * @param kDifuso O coeficiente de difusão da luz
	 */
	public LuzPonto(Vetor3D pos, Cor corLuz, double kDifuso) {
		this.pos = pos;
		this.corLuz = corLuz;
		this.kDifuso = kDifuso;
	}
	
	/**
	 * Ilumina um ponto de uma superfície.
	 * @param ponto O ponto a ser iluminado
	 * @param normal A normal da superfície no ponto escolhido
	 * @return A cor refletida pelo ponto iluminado
	 */
	public Cor iluminar(Vetor3D ponto, Vetor3D normal) {
		Vetor3D l = (pos.sub(ponto)).normalizado();
		double inclinacao = (normal.prodEscalar(l) > 0) ? normal.prodEscalar(l) : -normal.prodEscalar(l);
		return corLuz.prod(kDifuso * inclinacao);
	}
}
