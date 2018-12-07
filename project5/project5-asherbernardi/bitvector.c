#include <stdlib.h>

#include "bitvector.h"

struct bit_vector *
createBitVector(int size)
{
	struct bit_vector *ret;
	unsigned char *arr = malloc(size/8 + 1);
	ret.size = size;
	ret.vector = arr;
	ret = malloc(sizeof ret);
	return ret;
}

void
destroy(struct bit_vector *v)
{
	free v.vector;
	free v;
}

int
contains(struct bit_vector *v, int i)
{
	return -1;
}

void
insert(struct bit_vector *v, int i)
{

}

void
removeV(struct bit_vector *v, int i)
{

}

struct bit_vector *
complement(struct bit_vector *v)
{
	return NULL;
}

struct bit_vector *
unionV(struct bit_vector *v1, struct bit_vector *v2)
{
	return NULL;
}

struct bit_vector *
intersection(struct bit_vector *v1, struct bit_vector *v2)
{
	return NULL;
}

struct bit_vector *
difference(struct bit_vector *v1, struct bit_vector *v2)
{
	return NULL;
}
