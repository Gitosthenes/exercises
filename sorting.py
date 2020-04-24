from random import randint
from random import seed


def generate_array():
    test = []
    for num in range(20):
        seed(randint(0, 1000))
        test.append(randint(0, 100))
    return test


def selection_sort(my_list):
    for i in range(len(my_list) - 1):
        minVal = i
        for j in range(i + 1, len(my_list)):
            if my_list[j] < my_list[minVal]:
                minVal = j

        if(minVal != i):
            swap(i, minVal, my_list)


def insertion_sort(my_list):
    for i in range(len(my_list)):
        for j in range(i, 0, -1):
            if my_list[j] < my_list[j-1]:
                swap(j, j-1, my_list)


def swap(first, second, my_list):
    temp = my_list[first]
    my_list[first] = my_list[second]
    my_list[second] = temp


# Generate random array of integers:
selection_test = generate_array()
insertion_test = generate_array()

# Record before:
s_test_before = str(selection_test)
i_test_before = str(insertion_test)

# Sort:
selection_sort(selection_test)
insertion_sort(insertion_test)

# Show results:
print('Selection Sort:\n' + s_test_before + '\n' + str(selection_test))
print('---------------------')
print('Insertion Sort:\n' + i_test_before + '\n' + str(insertion_test))
