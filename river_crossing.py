FOX = 4
GOOSE= 2
SEEDS = 1

class State:
    def __init__(self, curr_path, t_shore, b_shore, boat):
        self.curr_path = curr_path
        self.t_shore = t_shore
        self.b_shore = b_shore
        self.boat = boat

    def generate_moves(self):
        possible_moves = []
        new_path = self.curr_path.copy()
        new_path.append(self)

        # Find boat:
        if self.boat == 'T':
            # Move boat without cargo
            possible_moves.append(State(new_path, self.t_shore, self.b_shore, 'B'))
            # If fox is present, move them
            if self.t_shore >= 4:
                possible_moves.append(State(new_path, self.t_shore - FOX, self.b_shore + FOX, 'B'))
            # If goose present, move them
            if self.t_shore in [2,3,6,7]:
                possible_moves.append(State(new_path, self.t_shore - GOOSE, self.b_shore + GOOSE, 'B'))
            # If seeds present, move them
            if self.t_shore % 2 == 1:
                possible_moves.append(State(new_path, self.t_shore - SEEDS, self.b_shore + SEEDs, 'B'))
        else:
            # Move boat without cargo
            possible_moves.append(State(new_path, self.t_shore, self.b_shore, 'T'))
            # If fox is present, move them
            if self.t_shore >= 4:
                possible_moves.append(State(new_path, self.t_shore - FOX, self.b_shore + FOX, 'T'))
            # If goose present, move them
            if self.t_shore in [2,3,6,7]:
                possible_moves.append(State(new_path, self.t_shore - GOOSE, self.b_shore + GOOSE, 'T'))
            # If seeds present, move them
            if self.t_shore % 2 == 1:
                possible_moves.append(State(new_path, self.t_shore - SEEDS, self.b_shore + SEEDs, 'T'))

        # Remove duplicates to prevent cycles
        for move in possible_moves:
            if move in self.curr_path:
                possible_moves.remove(move)

        return possible_moves

    def visual(self):
        string = format(self.t_shore, 'b')
        if self.boat == 'T':
            string += 'b'
        string += '\n~~~~\n' + format(self.b_shore, 'b')
        if self.boat == 'B':
            string += 'b'

        return string


    def __eq__(self, other):
        ans = False
        if isinstance(other, State):
            ans = self.t_shore == other.t_shore \
                and self.b_shore == other.b_shore \
                and self.boat == other.boat
        return ans


init_state = State([], 7, 0, 'T')
test_state = State([], 7, 0, 'T')

print(init_state == test_state)
print(init_state.visual())