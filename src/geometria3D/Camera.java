package geometria3D;

public class Camera {
	private Vetor3D c;
	private Vetor3D n, v;
	private double hX, hY;
	public final short xTela, yTela;
	public int[][] tela;
	private double[][] zBuffer;
	
	public Camera(Vetor3D c, Vetor3D n, Vetor3D v, double hX, double hY, double d, short xTela, short yTela) {
		this.c = c;
		this.n = n.normalizado(); //Incluir projeção
		this.v = v.normalizado();
		this.hX = hX / d;
		this.hY = hY / d;
		this.xTela = xTela;
		this.yTela = yTela;
		tela = new int[xTela][yTela];
		zBuffer = new double[xTela][yTela];
	}
	
	public void verTriangulo(Triangulo3D t) {
		t.translacao(Vetor3D.ZERO.sub(c));
		rastreamento(t);
	}
	
	public void rastreamento(Triangulo3D t) {
		for (int i = 0; i < xTela; i++) {
			for (int j = 0; j < yTela; j++) {
				Vetor3D ponto = t.colisao(new Vetor3D((2 * ((double)i / xTela) - 1) * hX, (1 - 2 * ((double)j / yTela)) * hY, -1), i == 0 && j == 0);
				//Iluminação e definição da cor do ponto
				if (ponto != null && (zBuffer[i][j] == 0 || zBuffer[i][j] > ponto.z) && ponto.z <= 0) {
					tela[i][j] = Integer.MAX_VALUE;
					zBuffer[i][j] = ponto.z;
				}
				/*if (i == 0 && j == 0) {
					Vetor3D temp = new Vetor3D((2 * ((double)i / xTela) - 1) * hX, (1 - 2 * ((double)j / yTela)) * hY, -1);
					System.out.println("Mira: "+temp.x+", "+temp.y+", "+temp.z);
					System.out.println("Ponto: "+ponto.x+", "+ponto.y+", "+ponto.z);
					System.out.printf("tela[%3d][%3d] = %d\n", i, j, tela[i][j]);
				}*/
			}
		}
	}
}
