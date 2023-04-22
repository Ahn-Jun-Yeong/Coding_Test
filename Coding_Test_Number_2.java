public class CoinChange {

    public static int getCoinChange(int[] coins, int sum) {
        // coinChange[i]는 합계 i를 만들 수 있는 동전의 조합 수를 의미한다.
        int[] coinChange = new int[sum + 1];
        coinChange[0] = 1; // 합계 0은 동전의 조합을 하나도 사용하지 않는 경우 1가지이다.
        for (int coin : coins) {
            for (int i = coin; i <= sum; i++) {
                // i번째 인덱스까지 사용 가능한 coin으로 만들 수 있는 조합의 수를 구한다.
                coinChange[i] += coinChange[i - coin];
            }
        }
        return coinChange[sum];
    }

    public static void main(String[] args) {
        int[] coins = {1, 2, 3};
        int sum = 4;
        System.out.println(getCoinChange(coins, sum)); // 4

        coins = new int[]{2, 5, 3, 6};
        sum = 10;
        System.out.println(getCoinChange(coins, sum)); // 5
    }
}