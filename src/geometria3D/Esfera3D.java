package geometria3D;

import luz.Cor;

public class Esfera3D extends Forma3D {
	public Vetor3D pos;

	public double raio;

	private Cor cor;

	public Esfera3D(Vetor3D pos, double raio, Cor cor) {
		this.pos = pos;
		this.raio = raio;
		this.cor = (cor != null) ? cor : Cor.BRANCO;
	}

	public Cor getCor() {
		return cor;
	}

	public Vetor3D normal(Vetor3D v) {
		return v.sub(pos).normalizado();
	}

	public Esfera3D translacao(Vetor3D v) {
		return new Esfera3D(pos.sub(v), raio, cor);
	}

	public Vetor3D colisao(Vetor3D v) {
		Vetor3D maisProx = pos.projetarSobre(v);
		Vetor3D dist = maisProx.sub(pos);
		if (contido(dist)) {
			Vetor3D ponto = maisProx.mult(1 - (raio * raio - dist.modulo() * dist.modulo()));
			return ponto;
		} else return null;
	}

	private boolean contido(Vetor3D p) {
		return p.modulo() <= raio;
	}
}
