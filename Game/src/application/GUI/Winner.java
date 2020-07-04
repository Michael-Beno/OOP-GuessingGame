package application.GUI;

import java.io.Serializable;

public class Winner implements Serializable {

		private static final long serialVersionUID = 1L;
		private String name;
		private String prize;

		public Winner(String name, String prize) { this.name = name; this.prize = prize; }
		public String getName() { return name; }
		public void setName(String name) { this.name = name; }
		public String getPrize() { return prize; }
		public void setPrize(String prize) { this.prize = prize; }

}
