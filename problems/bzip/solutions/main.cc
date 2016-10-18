#include <iostream>
#include <cstdint>

// Why didn't I write this validation in Vigil? It has "swear" and "implore"
int main()
{
	try
	{
		std::uint16_t numBits = 0u;
		std::cin >> numBits;

		if (numBits > 32u)
		{
			throw "Input out of bounds - numBits greater than 32";
		}

		std::uint64_t a = 0u, b = 0u;

		std::cin >> a >> b;

		// Test a and b against the number of bits (detect broken problems)
		if ((a >> numBits) > 0ull)
		{
			throw "First input number out of bounds";
		}

		if (a > ((1ull << 32ull)) - 1ull)
		{
			throw "First number does not fit in 32 bits";
		}

		if ((b >> numBits) > 0ull)
		{
			throw "Second input number out of bounds";
		}

		if (b > ((1ull << 32ull)) - 1ull)
		{
			throw "Second number does not fit in 32 bits";
		}

		// Zip with XOR and then negate result.
		//  Shift that result over by (64 - numBits) bits
		//  to get rid of inappropriate trailing 1s
		std::uint64_t result = ((~(a ^ b)) << (64 - numBits));
		if (result > 0ull)
		{
			std::cout << "NO" << std::endl;
		}
		else
		{
			std::cout << "YES" << std::endl;
		}
	}
	catch (const char* err)
	{
		std::cerr << "TEST FAILED TO UPHOLD CONSTRAINT: " << err << std::endl;
	}

	return 0;
}