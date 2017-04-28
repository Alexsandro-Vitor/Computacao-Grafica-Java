package geometria3D;

public class Camera {
	private Vetor3D c;
	private Vetor3D n, v, r;
	private double hX, hY;
	public final short xTela, yTela;
	public int[][] tela;
	private double[][] zBuffer;
	
	public Camera(Vetor3D c, Vetor3D n, Vetor3D v, double hX, double hY, double d, short xTela, short yTela) {
		this.c = c;
		this.v = v.normalizado();
		this.n = n.sub(n.projetarSobre(v)).normalizado();	//Este vetor aponta para acima da camera
		this.r = v.prodVetorial(n).normalizado();			//Este vetor aponta para a direita da camera
		this.hX = hX / d;
		this.hY = hY / d;
		this.xTela = xTela;
		this.yTela = yTela;
		tela = new int[xTela][yTela];
		zBuffer = new double[xTela][yTela];
	}
	
	public void verTriangulo(Triangulo3D t) {
		t = t.translacao(c);
		rastreamento(t);
	}
	
	public void rastreamento(Triangulo3D t) {
		for (int i = 0; i < xTela; i++) {
			for (int j = 0; j < yTela; j++) {
				Vetor3D x = r.mult((2 * ((double)i / xTela) - 1) * hX);
				Vetor3D y = n.mult((1 - 2 * ((double)j / yTela)) * hY);
				Vetor3D ponto = t.colisao(v.soma(x).soma(y));
				//Iluminação e definição da cor do ponto
				if (ponto != null && (zBuffer[i][j] == 0 || zBuffer[i][j] > ponto.z) && ponto.z <= 0) {
					tela[i][j] = -1;
					zBuffer[i][j] = ponto.z;
				}
			}
		}
	}
}
