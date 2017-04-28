package geometria3D;

/**
 * Classe que representa uma c�mera que v� objetos tridimensionais
 * @since v0.2.0
 * @author Alexsandro V�tor Serafim de Carvalho - avsc@cin.ufpe.br
 */
public class Camera {
	/**
	 * Posi��o da c�mera no espa�o
	 */
	private Vetor3D c;
	
	/**
	 * Vetor que aponta para acima da c�mera
	 */
	private Vetor3D n;
	
	/**
	 * Vetor que representa a orienta��o da c�mera
	 */
	private Vetor3D v;
	
	/**
	 * Vetor que aponta para a direita da c�mera
	 */
	private Vetor3D r;
	
	/**
	 * Alcance horizontal da c�mera
	 */
	private double hX;
	
	/**
	 * Alcance vertical da c�mera
	 */
	private double hY;
	
	/**
	 * Largura da tela, em pixels
	 */
	public final short xTela;
	
	/**
	 * Altura da tela, em pixels
	 */
	public final short yTela;
	
	/**
	 * Matriz que representa as cores de cada pixel da tela
	 */
	public int[][] tela;
	
	/**
	 * Matriz que armazena a distancia da c�mera para cada ponto exibido na tela
	 */
	private double[][] zBuffer;
	
	/**
	 * Cria uma c�mera com cada um dos atributos abaixo.
	 * @param c Posi��o da c�mera
	 * @param n Para onde o topo da c�mera aponta
	 * @param v Para onde a c�mera aponta
	 * @param hX Alcance horizontal da c�mera
	 * @param hY Alcance vertical da c�mera
	 * @param xTela Largura da tela, em pixels
	 * @param yTela Altura da tela, em pixels
	 * @since v0.2.0
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
	 * Exibe um tri�ngulo na tela
	 * @param t O tri�ngulo a ser exibido
	 * @since v0.2.0
	 * @see Triangulo3D
	 */
	public void verTriangulo(Triangulo3D t) {
		t = t.translacao(c);
		rastreamento(t);
	}
	
	/**
	 * Para cada pixel da tela, checa se algum ponto do tri�ngulo deve aparecer nele
	 * @param t O tri�ngulo a ser exibido
	 * @since v0.2.0
	 */
	private void rastreamento(Triangulo3D t) {
		for (int i = 0; i < xTela; i++) {
			for (int j = 0; j < yTela; j++) {
				Vetor3D x = r.mult((2 * ((double)i / xTela) - 1) * hX);
				Vetor3D y = n.mult((1 - 2 * ((double)j / yTela)) * hY);
				Vetor3D ponto = t.colisao(v.soma(x).soma(y));
				//Ilumina��o e defini��o da cor do ponto
				if (ponto != null && (zBuffer[i][j] == 0 || zBuffer[i][j] > ponto.z) && ponto.z <= 0) {
					tela[i][j] = -1;
					zBuffer[i][j] = ponto.z;
				}
			}
		}
	}
}
