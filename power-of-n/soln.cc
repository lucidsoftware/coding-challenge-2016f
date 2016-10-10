#include <iostream>
#include <cstdint>

int main(int argc, char** argv)
{
    std::uint32_t k = 0u, n = 0u;

    std::cin >> k >> n;

    // First case: If n is 1, k can only be a power of n if k is also 1.
    //  I really hope people catch that, given the image displayed at the top.
    // I'm not testing for negative numbers or 0, since the problem constaints
    //  specify that those will not be given as input
    if (n == 1u)
    {
        if (k == 1u)
        {
            std::cout << "YES" << std::endl;
        }
        else
        {
            std::cout << "NO" << std::endl;
        }
        return 0;
    }

    // While k is divisible by n, divide k by n.
    // Stop when k is no longer divisible by n
    // Iff k is exactly one, that means that it was a power of n
    while (k % n == 0)
    {
        k /= n;
    } 

    if (k == 1)
    {
        std::cout << "YES" << std::endl;
    }
    else
    {
        std::cout << "NO" << std::endl;
    }

    return 0;
}