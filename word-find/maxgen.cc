#include <iostream>
#include <ctime>
#include <random>

int main()
{
    srand(time(0));
    std::cout << 2000 << " " << 2000 << std::endl;
    for (unsigned int i = 0u; i < 2000u; i++)
    {
        for (unsigned int j = 0u; j < 2000u; j++)
        {
            char randLetter = rand() % ('Z' - 'A') + 'A';
            std::cout << randLetter << " "; 
        }
        std::cout << std::endl;
    }

    return 0;
}