package luz;

/**
 * Classe que representa uma cor opaca.
 * @since v0.3.0
 * @author Alexsandro Vítor Serafim de Carvalho - avsc@cin.ufpe.br
 */
public class Cor {
	public static final Cor PRETO = new Cor(0, 0, 0);
	public static final Cor VERMELHO = new Cor(255, 0, 0);
	public static final Cor VERDE = new Cor(0, 255, 0);
	public static final Cor AZUL = new Cor(0, 0, 255);
	public static final Cor CIANO = new Cor(0, 255, 255);
	public static final Cor ROXO = new Cor(255, 0, 255);
	public static final Cor AMARELO = new Cor(255, 255, 0);
	public static final Cor BRANCO = new Cor(255, 255, 255);
	
	private short red;
	private short green;
	private short blue;
	
	/**
	 * Construtor que recebe um int e o converte para uma cor RGB.
	 * @param rgb O inteiro a ser convertido em cor
	 */
	public Cor (int rgb) {
		red = (short)red(rgb);
		green = (short)green(rgb);
		blue = (short)blue(rgb);
	}
	
	/**
	 * Construtor que recebe os parametros de uma cor e a gera.
	 * @param red Componente vermelho da cor
	 * @param green Componente verde da cor
	 * @param blue Componente azul da cor
	 */
	public Cor(int red, int green, int blue) {
		this.red = (short)entre0e255(red);
		this.green = (short)entre0e255(green);
		this.blue = (short)entre0e255(blue);
	}
	
	private static int red(int argb) {
		return Integer.remainderUnsigned(Integer.divideUnsigned(argb, 65536), 256);
	}
	
	private static int green(int argb) {
		return Integer.remainderUnsigned(Integer.divideUnsigned(argb, 256), 256);
	}
	
	private static int blue(int argb) {
		return Integer.remainderUnsigned(argb, 256);
	}
	
	/**
	 * Soma duas cores a e b.
	 * @param a
	 * @param b
	 * @return Uma cores que corresponte a soma das duas
	 */
	public static Cor soma(Cor a, Cor b) {
		int red = (short)entre0e255(a.red + b.red);
		int green = (short)entre0e255(a.green + b.green);
		int blue = (short)entre0e255(a.blue + b.blue);
		return new Cor(red, green, blue);
	}
	
	/**
	 * Filtra uma cor com outra.
	 * Esse método é utilizado para iluminar superfícies coloridas, onde uma das cores é a luz incidente e a outra é a cor do objeto iluminado.
	 * @param a
	 * @param b
	 * @return A cor resultante da mistura de uma com a outra
	 */
	public static Cor filtro(Cor a, Cor b) {
		int red = Integer.divideUnsigned(a.red * b.red, 255);
		int green = Integer.divideUnsigned(a.green * b.green, 255);
		int blue = Integer.divideUnsigned(a.blue * b.blue, 255);
		return new Cor(red, green, blue);
	}
	
	private static int entre0e255(int i) {
		if (i < 0) return 0;
		else return (i <= 255) ? i : 255;
	}

	/**
	 * Mutliplica uma cor por um número.
	 * @param d
	 * @return A cor multiplicada
	 */
	public Cor prod(double d) {
		return new Cor((int)(red * d), (int)(green * d), (int)(blue * d));
	}
	
	/**
	 * Converte a cor em um numero inteiro de 32 bits, onde os bits [23-16] representam a cor vermelha,
	 * os bits [15-8] representam a cor verde e os bits [7-0] representam a cor azul.
	 * @return Um número inteiro que representa a cor
	 */
	public int toInt() {
		return ((255 * 256 + red) * 256 + green) * 256 + blue;
	}
	
	public String toString() {
		return "("+red+", "+green+", "+blue+")";
	}
}
