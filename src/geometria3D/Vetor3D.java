package geometria3D;

/**
 * Classe que representa pontos e vetores de tr�s dimens�es.
 * @since v0.2.0
 * @author Alexsandro V�tor Serafim de Carvalho - avsc@cin.ufpe.br
 */
public class Vetor3D {
	/**
	 * Vetor nulo
	 */
	public static final Vetor3D ZERO = new Vetor3D(0, 0, 0);
	
	/**
	 * Coordenada x do vetor
	 */
	public double x;
	
	/**
	 * Coordenada y do vetor
	 */
	public double y;
	
	/**
	 * Coordenada z do vetor
	 */
	public double z;
	
	/**
	 * Cria um vetor com os valores das suas coordenadas
	 * @param x Coordenada x do vetor
	 * @param y Coordenada y do vetor
	 * @param z Coordenada z do vetor
	 */
	public Vetor3D(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	/**
	 * Soma cada coordenada de dois vetores. Em linguagem matem�tica: this + v = retorno.
	 * @param v O vetor a ser somado
	 * @return Um novo vetor que � a soma deste vetor com o passado como par�metro
	 */
	public Vetor3D soma(Vetor3D v) {
		return new Vetor3D(x + v.x, y + v.y, z + v.z);
	}
	
	/**
	 * Subtrai das coordenadas deste vetor os valores das coordenadas de outro vetor. Em linguagem matem�tica: this - v = retorno.
	 * @param v O vetor que subtrair� o vetor no qual essa fun��o � chamada
	 * @return Um novo vetor que � o resultado de subtrair o vetor passado como par�metro do vetor no qual essa fun��o � chamada
	 */
	public Vetor3D sub(Vetor3D v) {
		return new Vetor3D(x - v.x, y - v.y, z - v.z);
	}
	
	/**
	 * Multiplica um vetor por um valor num�rico. Em linguagem matem�tica: this * i = retorno.
	 * @param i O valor pelo qual todos os parametros do vetor ser�o multiplicados
	 * @return Um novo vetor cujas coordenadas s�o as do vetor mulitplicadas por i
	 */
	public Vetor3D mult(double i) {
		return new Vetor3D(x * i, y * i, z * i);
	}
	
	/**
	 * Realiza uma multiplica��o escalar entre dois vetores.
	 * @param v O outro vetor a ser multiplicado
	 * @return A soma dos produtos de cada coordenada de ambos os vetores
	 */
	public double prodEscalar(Vetor3D v) {
		return x * v.x + y * v.y + z * v.z;
	}
	
	/**
	 * Calcula o produto vetorial entre dois vetores.
	 * @param v O outro vetor a gerar o produto
	 * @return Um vetor perpendicular a ambos os vetores
	 */
	public Vetor3D prodVetorial(Vetor3D v) {
		return new Vetor3D(y * v.z - z * v.y, z * v.x - x * v.z, x * v.y - y * v.x);
	}
	
	/**
	 * Divide todos as coordenadas de um vetor por um valor num�rico. Em linguagem matem�tica: this / i = retorno.
	 * @param i O n�mero divisor
	 * @return Um novo vetor cujas coordenadas s�o as coordenadas do vetor divididas por i
	 */
	public Vetor3D div(double i) {
		return new Vetor3D(x / i, y / i, z / i);
	}
	
	/**
	 * Projeta um vetor sobre o passado como par�metro
	 * @param v O vetor sobre o qual outro vetor ser� projetado
	 * @return Um novo vetor que � a proje��o deste vetor sobre o vetor v
	 */
	public Vetor3D projetarSobre(Vetor3D v) {
		return v.mult(prodEscalar(v) / v.prodEscalar(v));
	}
	
	/**
	 * Retorna o m�dulo ou norma de um vetor.
	 * @return O m�dulo do vetor
	 */
	public double modulo() {
		return Math.sqrt(x * x + y * y + z * z);
	}
	
	/**
	 * Retorna o vetor normalizado.
	 * @return Um novo vetor com o mesmo sentido deste, por�m cujo m�dulo � igual a 1
	 */
	public Vetor3D normalizado() {
		return (modulo() != 0) ? div(modulo()) : Vetor3D.ZERO;
	}
	
	/**
	 * Retorna uma String que representa o vetor.
	 * @param nome Um nome para identificar o vetor representado
	 * @return Uma string contendo as coordenadas do vetor
	 */
	public String toString(String nome) {
		return nome + " = (" + this.x + ", " + this.y + ", " + this.z + ")";
	}
}
