package green.opportunity;

public class GetResponse {

	MIImages[] mi_images;
	PCamImages[] pcam_images;
	NCamImages[] ncam_images;
	FCamImages[] fcam_images;

	public FCamImages[] getFcam_images() {
		return fcam_images;
	}

	public NCamImages[] getNcam_images() {
		return ncam_images;
	}

	public PCamImages[] getPcam_images() {
		return pcam_images;
	}

	public MIImages[] getMi_images() {
		return mi_images;
	}

}
