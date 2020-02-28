#include <iostream>
#include <algorithm>
#include <vector>
#include <map>
#include <set>

using namespace std;


int main() {
    int q;
    cin >> q;
    for (int w = 0; w < q; w++) {
        int n;
        cin >> n;
        vector<int> v;
        long long last = INT32_MAX, propavshee = INT32_MAX;
        for (int i = 0; i < n; i++) {
            int a;
            cin >> a;
            if (propavshee == -1) {
                if (last != INT32_MAX)
                    v.push_back(last);
                v.push_back(a);
                last = propavshee;
                propavshee = 0;
            } else {
                last = propavshee;
                propavshee = a;
            }
        }
        long long sum = 0;
        for (auto i : v)
            sum += i;
        long long k = long (sum / v.size());
        long long max = 0;
        for (auto i : v) {
            long long temp = abs(i - k);
            if (temp > max)max = temp;
        }
        cout << k << ' ' << max << endl;

    }
}
