#include <iostream>
#include <vector>
using namespace std;

// function to heapify the tree
void heapify(int arr[], int n, int root)
{
    // build heapify
    int max = root;
    int left = 2 * root + 1;
    int right = 2 * root + 2;

    if (left < n && arr[left] > arr[max])
    {
        max = left;
    }

    if (right < n && arr[right] > arr[max])
    {
        max = right;
    }

    if (max != root)
    {
        swap(arr[root], arr[max]);
        heapify(arr, n, max);
    }
}

// implementing heap sort
void heapSort(int arr[], int n)
{
    // build heap
    for (int i = n / 2 - 1; i >= 0; i--)
    {
        heapify(arr, n, i);
    }

    // Extracting elements from the heap one by one
    for (int i = n - 1; i >= 0; i--)
    {
        swap(arr[0], arr[i]);
        heapify(arr, i, 0);
    }
}

/* print contents of array */
void displayArray(int arr[], int n)
{
    for (int i = 0; i < n; ++i)
        cout << arr[i] << " ";
    cout << "\n";
}

// main program

int main()
{
    vector<int> arr;
    int a;
    cout << "Input array" << endl;
    while (cin >> a)
    {
        arr.push_back(a);
    }
    int n = arr.size();

    int heap_arr[n];
    for (int i = 0; i < n; i++)
    {
        heap_arr[i] = arr[i];
    }
    cout << "Input array" << endl;
    displayArray(heap_arr, n);

    heapSort(heap_arr, n);

    cout << "Sorted array" << endl;
    displayArray(heap_arr, n);
}
