import random

def generate_random_dna_sequence(length):
    bases = 'ATCG'
    sequence = ''.join(random.choice(bases) for _ in range(length))
    return sequence

# Przykładowe użycie
sequence_length = 1000
random_sequence = generate_random_dna_sequence(sequence_length)
print(random_sequence)