class Solution {
    
    public int solution(int n, int[] cores) {
        int left = 0;
        int right = 25000 * 10000;
        int processingTime = 0;
        long totalTask = 0;

        while (left <= right) {
            int mid = (left + right) / 2;
            long capacity = countCapacity(mid, cores);

            if(capacity >= n) {
                processingTime = mid;
                right = mid - 1;
                totalTask = capacity;
            } else {
                left = mid + 1;
            }
        }

        int ans = 1;
        for (int i = cores.length - 1; i >= 0; i--) {
            if (processingTime % cores[i] == 0) {
                if (totalTask == n) {
                    ans = i + 1;
                    break;
                }

                totalTask--;
            }
        }

        return ans;
    }

    private long countCapacity(int time, int[] cores) {
        if (time == 0)
            return cores.length;

        long capacity = cores.length;
        for (int core : cores) {
            capacity += (time / core);
        }

        return capacity;
    }
}