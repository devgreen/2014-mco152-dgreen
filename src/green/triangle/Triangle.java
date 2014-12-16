package green.triangle;

public class Triangle {
	private int height;
	StringBuilder triangle = new StringBuilder();

	public Triangle(int height) {

		this.height = height;
		int lines2 = this.height - 1;
		int frontSpaces = lines2;

		for (int i = 0; i < frontSpaces; i++) {
			triangle.append(' ');
		}

		triangle.append('*');
		triangle.append('\n');

		int midSpace = 1;
		for (int i = 1; i < lines2; i++) {
			int spaces = height - i;

			for (int j = 1; j < spaces; j++) {
				triangle.append(' ');
			}

			triangle.append('*');

			for (int l = 1; l <= midSpace; l++) {
				triangle.append(' ');
			}

			triangle.append('*');

			midSpace = midSpace + 2;

			triangle.append("\n");

		}

		int lastLineStars = height + lines2;
		int finalStars = lastLineStars;

		for (int p = 1; p <= finalStars; p++) {
			triangle.append('*');
		}

	}

	public String toString() {
		return triangle.toString();
	}

	public static void main(String args[]) {
		Triangle triangle = new Triangle(100);
		System.out.println(triangle.toString());
	}

}
