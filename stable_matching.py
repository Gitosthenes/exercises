"""
Custom Implemetation of the Gale-Shapley Algorithm to 
produce stable matches between companies and prospective students.
"""

from faker import Faker
from random import shuffle, randint

# Change number of pairs to generate here:
NUM_PAIRS = 10

""" Linked List Implementation """
# Used over lists to optimize frequent
# removal of first element.
class Link:

    def __init__(self, first, rest=None):
        assert rest is None or isinstance(rest, Link)
        self.first = first
        self.rest = rest

    def remove_head(self):
        return self.rest


""" Company & Student Class Definitions """


class Company:

    def __init__(self, name, prospects):
        self.name = name
        self.prospects = prospects
        self.crossed_off_names = []
        self.curr_pair = None


class Student:

    def __init__(self, name, rankings):
        self.name = name
        self.rankings = rankings
        self.curr_pair = None


""" Gale-Shapely Algorithm """
# Produces a stable set of matches between two sets
# of entities (in this case companies and students)
def generate_matches(companies):
    while(not_done(companies)):
        for comp in companies:
            if comp.curr_pair is None:
                curr_prospect = comp.prospects.first
                comp.crossed_off_names.append(curr_prospect.name)
                comp.prospects = comp.prospects.remove_head()
                if curr_prospect.curr_pair is None:
                    comp.curr_pair = curr_prospect
                    curr_prospect.curr_pair = comp
                else:
                    this_rank = curr_prospect.rankings[comp.name]
                    other_rank = curr_prospect\
                        .rankings[curr_prospect.curr_pair.name]

                    if this_rank < other_rank:
                        curr_prospect.curr_pair.curr_pair = None
                        comp.curr_pair = curr_prospect
                        curr_prospect.curr_pair = comp


""" Helper Functions """

# Recursively converts any list into a custom Linked List
def list_to_ll(lst):
    if len(lst) == 1:
        return Link(lst[0])
    return Link(lst[0], list_to_ll(lst[1:]))

# Generates a list of randomized names using Faker. The 'func' parameter
# determines what kind of names to generate.
# https://faker.readthedocs.io/en/master/
def generate_name_list(func):
    count = NUM_PAIRS
    name_lst = []

    while count > 0:
        Faker.seed(randint(0, 1000))
        name = func()
        if name not in name_lst:
            name_lst.append(name)
            count -= 1

    return name_lst

# Returns true if any company has no current tenative pairing; false otherwise
def not_done(companies):
    result = False
    for comp in companies:
        if comp.curr_pair is None:
            result = True
            break
    return result

# Verifies that all matches made are truly stable
# Returns False if, for any 2 pairs the following is true:
#   - The company from pair A prefers the student from pair B over the student they are currently paired with.
#   - The Student from pair B prefers the company from pair A over the company they are currently paired with.
#   - And vice-versa 
def check_matches(companies):
    stable = True
    for i in range(len(companies)):
        for j in range(i + 1, len(companies)):
            compA = companies[i]
            compB = companies[j]
            studA = companies[i].curr_pair
            studB = companies[j].curr_pair

            compA_prefers_studB = studB.name in compA.crossed_off_names
            compB_prefers_studA = studA.name in compB.crossed_off_names
            studB_prefers_compA = studB.rankings[compA.name]\
                < studB.rankings[studB.curr_pair.name]
            studB_prefers_compA = studA.rankings[compB.name]\
                < studA.rankings[studA.curr_pair.name]

    if (compA_prefers_studB and studB_prefers_compA)\
            or (compB_prefers_studA and studB_prefers_compA):
        stable = False

    return stable

# Controller function where program begins.
def main():
    fake = Faker()

    # Generate Company & Student names:
    comp_names = generate_name_list(fake.company)
    stud_names = generate_name_list(fake.name)

    # Generate Company and Student Objects:
    companies = []
    students = []

    for name in stud_names:
        shuffle(comp_names)
        rankings = {k: v for v, k in enumerate(comp_names)}
        students.append(Student(name, rankings))

    for comp in comp_names:
        shuffle(students)
        prospects = list_to_ll(students)
        companies.append(Company(comp, prospects))

    # Run G.S. algorithm
    generate_matches(companies)

    # Show results:
    for comp in companies:
        print(str(comp.name) + ' <------> ' + str(comp.curr_pair.name))
    print('\nALL MATCHES STABLE: ' + str(check_matches(companies)).upper())


if __name__ == "__main__":
    main()
