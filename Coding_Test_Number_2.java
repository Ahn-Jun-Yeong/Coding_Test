public class CoinChange {

    public static int getCoinChange(int[] coins, int sum) {
        // coinChange[i]�� �հ� i�� ���� �� �ִ� ������ ���� ���� �ǹ��Ѵ�.
        int[] coinChange = new int[sum + 1];
        coinChange[0] = 1; // �հ� 0�� ������ ������ �ϳ��� ������� �ʴ� ��� 1�����̴�.
        for (int coin : coins) {
            for (int i = coin; i <= sum; i++) {
                // i��° �ε������� ��� ������ coin���� ���� �� �ִ� ������ ���� ���Ѵ�.
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