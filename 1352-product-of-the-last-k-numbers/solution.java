// 17 ms | 84.5 MB

class ProductOfNumbers {

    ArrayList<Integer> list;

    public ProductOfNumbers() {
        list = new ArrayList<>();
    }

    public void add(int num) {

        if (num == 0) {
            list.clear();
        } else {
            int product = num;

            if (!list.isEmpty()) {
                product = list.get(list.size() - 1) * num;
            }

            list.add(product);
        }
    }

    public int getProduct(int k) {

        int n = list.size();

        if (k > n) {
            return 0;
        }

        if (k == n) {
            return list.get(n - 1);
        }

        return list.get(n - 1) / list.get(n - k - 1);
    }
}