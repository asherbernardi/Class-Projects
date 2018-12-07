#ifndef BIT_VECTOR_H
#define BIT_VECTOR_H

struct bit_vector {
	int size;
	unsigned char *vector;
};

typedef struct bit_vector bit_vector_t;

struct bit_vector *createBitVector(int size);

void destroy(struct bit_vector *v);

void insert(struct bit_vector *v, int i);

void removeV(struct bit_vector *v, int i);

int contains(struct bit_vector *v, int i);

struct bit_vector *unionV(struct bit_vector *v1, struct bit_vector *v2);

struct bit_vector *intersection(struct bit_vector *v1, struct bit_vector *v2);

struct bit_vector *difference(struct bit_vector *v1, struct bit_vector *v2);

struct bit_vector *complement(struct bit_vector *v);

#endif
