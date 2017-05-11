package geometria3D;

import luz.Luzes;

/**
 * Classe que representa uma câmera que vê objetos tridimensionais.
 * @since v0.2.0
 * @author Alexsandro Vítor Serafim de Carvalho - avsc@cin.ufpe.br
 */
public class Camera {
	/**
	 * Posição da câmera no espaço.
	 */
	private Vetor3D c;
	
	/**
	 * Vetor que aponta para acima da câmera.
	 */
	private Vetor3D n;
	
	/**
	 * Vetor que representa a orientação da câmera.
	 */
	private Vetor3D v;
	
	/**
	 * Vetor que aponta para a direita da câmera.
	 */
	private Vetor3D r;
	
	/**
	 * Alcance horizontal da câmera.
	 */
	private double hX;
	
	/**
	 * Alcance vertical da câmera.
	 */
	private double hY;
	
	/**
	 * Largura da tela, em pixels.
	 */
	public final short xTela;
	
	/**
	 * Altura da tela, em pixels.
	 */
	public final short yTela;
	
	/**
	 * Matriz que representa as cores de cada pixel da tela.
	 */
	public int[][] tela;
	
	/**
	 * Matriz que armazena a distancia da câmera para cada ponto exibido na tela.
	 */
	private double[][] zBuffer;
	
	/**
	 * Cria uma câmera com cada um dos atributos abaixo.
	 * @param c Posição da câmera
	 * @param n Para onde o topo da câmera aponta
	 * @param v Para onde a câmera aponta
	 * @param hX Alcance horizontal da câmera
	 * @param hY Alcance vertical da câmera
	 * @param xTela Largura da tela, em pixels
	 * @param yTela Altura da tela, em pixels
	 * @see Vetor3D
	 */
	public Camera(Vetor3D c, Vetor3D n, Vetor3D v, double hX, double hY, short xTela, short yTela) {
		this.c = c;
		this.v = v.normalizado();
		this.n = n.sub(n.projetarSobre(v)).normalizado();
		this.r = v.prodVetorial(n).normalizado();
		this.hX = hX;
		this.hY = hY;
		this.xTela = xTela;
		this.yTela = yTela;
		tela = new int[xTela][yTela];
		zBuffer = new double[xTela][yTela];
	}
	
	/**
	 * Exibe um triângulo na tela.
	 * @param t O triângulo a ser exibido
	 * @see Triangulo3D
	 */
	public void verTriangulo(Triangulo3D t, Luzes luzes) {
		t = t.translacao(c);
		rastreamento(t, luzes);
	}
	
	/**
	 * Para cada pixel da tela, checa se algum ponto do triângulo deve aparecer nele.
	 * @param t O triângulo a ser exibido
	 */
	private void rastreamento(Triangulo3D t, Luzes luzes) {
		for (int i = 0; i < xTela; i++) {
			for (int j = 0; j < yTela; j++) {
				Vetor3D x = r.mult((2 * ((double)i / xTela) - 1) * hX);
				Vetor3D y = n.mult((1 - 2 * ((double)j / yTela)) * hY);
				Vetor3D ponto = t.colisao(v.soma(x).soma(y));
				//Iluminação e definição da luz do ponto
				if (ponto != null && (zBuffer[i][j] == 0 || zBuffer[i][j] > ponto.z) && ponto.z <= 0) {
					tela[i][j] = luzes.iluminar(ponto, t.normal(), t.cor).toInt();
					zBuffer[i][j] = ponto.z;
				}
			}
		}
	}
}
