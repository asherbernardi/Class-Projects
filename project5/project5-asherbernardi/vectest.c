#include <stdlib.h>
#include <stdio.h>

#include "bitvector.h"

char *
boolToString(int x)
{
	return x ? "yes" : "no";
}

void
printVec(struct bit_vector *vec)
{
	int isFirst, i;
	printf("{");
	isFirst = 1;
	for (i = 0; i < 10; i++)
		if (contains(vec, i)) {
			if (!isFirst)
				printf(", ");
			printf("%d", i);
			isFirst = 0;
		}
	printf("} \n");
}

int
main(void)
{
	int i;
	struct bit_vector *vec = createBitVector(10);

	/*  delete this line to test part 3-C

	printf("3C vec = ");   
	printVec(vec);


	/* delete this line to test part 3-D

	insert(vec, 5);

	printf("3Di vec = ");
	printVec(vec);

	insert(vec, 9);

	printf("3Dii vec = ");
	printVec(vec);

	/* delete this line to test part 3-E

	removeV(vec, 4);

	printf("3Ei vec = ");
	printVec(vec);


	removeV(vec, 5);

	printf("3Eii vec = ");
	printVec(vec);


	/* delete this line to test part 4-A

	insert(vec, 0);
	insert(vec, 3);
	insert(vec, 7);

	printf("4Ai vec = ");
	printVec(vec);

	struct bit_vector *vec2 = complement(vec);

	printf("4Aii vec = ");
	printVec(vec);

	printf("4Aiii vec2 = ");
	printVec(vec2);


	/* delete this line to test part 4-B

	removeV(vec2, 5);
	removeV(vec2, 8);
	insert(vec2, 3);
	insert(vec2, 9);

	printf("4Bi vec = ");
	printVec(vec);
	printf("4Bii vec2 = ");
	printVec(vec2);

	struct bit_vector *vec3 = unionV(vec, vec2);

	printf("4Biii vec3 = ");
	printVec(vec3);


	/* delete this line to test part 4-C

	printf("4Ci vec = ");
	printVec(vec);
	printf("4Cii vec2 = ");
	printVec(vec2);


	struct bit_vector *vec4 = intersection(vec, vec2);

	printf("4Ciii vec4 = ");
	printVec(vec4);


	/* delete this line to test part 4-D

	printf("4Di vec = ");
	printVec(vec);

	printf("4Dii vec2 = ");
	printVec(vec2);

	struct bit_vector *vec5 = difference(vec, vec2);

	printf("4Diii vec5 = ");
	printVec(vec5);

	destroy(vec5);
	destroy(vec4);
	destroy(vec3);
	destroy(vec2);


	*//* delete this line also when testing 4-D */
	destroy(vec);

	return 0;
}
