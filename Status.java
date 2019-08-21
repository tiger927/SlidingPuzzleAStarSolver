import java.util.Arrays;

public class Status implements Comparable<Status>{
	public static int[] dx = new int[]{1,0,-1,0};
	public static int[] dy = new int[]{0,1,0,-1};
	
	int[][] arr;
	int n;
	int x;
	int y;
	int scoreH = -1;
	int scoreG = 0;
	
	public Status(int[][] arr) {
		this.n = arr.length;
		this.arr = new int[n][n];
		for(int i = 0;i < n;i++) {
			for(int j = 0;j < n;j++) {
				if(arr[i][j] == 0) {
					this.x = i;
					this.y = j;
				}
				this.arr[i][j] = arr[i][j];
			}
		}
	}
	
	public long Hash() {
		long res = 0;
		for(int i = 0;i < this.n;i++) {
			for(int j = 0;j < this.n;j++) {
				res += arr[i][j] * Math.pow(3,j + i * this.n);
			}
		}
		return res;
	}
	
	
	public int Calc() {
		if(this.scoreH != -1) return scoreH;
		int res = 0;
		for(int i = 0;i < this.n;i++) {
			for(int j = 0;j < this.n;j++) {
				int shouldX = arr[i][j] / this.n;
				int shouldY = arr[i][j] % this.n;
				res += Math.abs(shouldX - i) + Math.abs(shouldY - j);
			}
		}
		this.scoreH = res;
		return res;
	}
	
	public Status move(int d) {
		Status res = new Status(this.arr);
		int newX = this.x + dx[d];
		int newY = this.y + dy[d];
		if(newX < 0 || newX >= this.n || newY < 0 || newY >= this.n) {
			res.scoreG = -1;
			return res;
		}
		res.arr[this.x][this.y] = res.arr[newX][newY];
		res.arr[newX][newY] = 0;
		res.x = newX;
		res.y = newY;
		res.Calc();
		res.scoreG = this.scoreG + 1;
		return res;
	}

	@Override
	public int compareTo(Status o) {
		//It seems that changing this formula can greatly help the performance of this algorithm
		return (4 * this.Calc() + this.scoreG - 4 * o.Calc() - o.scoreG);
	}
	
	
	public void print() {
		for(int i = 0;i < this.n;i++) {
			System.out.println(Arrays.toString(this.arr[i]));
		}
		System.out.println();
	}
	
	public void scrumble(int k) {
		if(k < 0) return;
		int d = (int)(Math.random() * 4);
		int newX = this.x + dx[d];
		int newY = this.y + dy[d];
		if(newX < 0 || newX >= this.n || newY < 0 || newY >= this.n) {
			scrumble(k);
		}else{
			arr[this.x][this.y] = arr[newX][newY];
			arr[newX][newY] = 0;
			this.x = newX;
			this.y = newY;
			scrumble(k - 1);
		}
	}
}
