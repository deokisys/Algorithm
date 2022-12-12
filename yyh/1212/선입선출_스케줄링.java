package yyh.programmers.level3;

import java.util.ArrayList;
import java.util.List;

public class 선입선출_스케줄링 {

	public int solution(int n, int[] cores) {

		int answer = 0;

		List<Core> list = new ArrayList<>();
		for (int i = 0; i < cores.length; i++) {
			list.add(new Core(i, cores[i]));
		}

		n -= cores.length;

		loop: while (true) {
			for (Core core : list) {
				core.time--;

				if (core.time == 0) {
					n--;
					list.set(core.index, new Core(core.index, cores[core.index]));

					if (n == 0) {
						answer = core.index + 1;
						break loop;
					}
				}
			}
		}

		return answer;
	}
}

class Core {
	int index;
	int time;

	public Core(int index, int time) {
		super();
		this.index = index;
		this.time = time;
	}
}
