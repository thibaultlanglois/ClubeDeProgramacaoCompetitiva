/*
 *  Authors:
 *  Márcio Milisse   nº 61799
 *  Afonso Henriques nº 61826
 */
 
#include <iostream>
#include <fstream>
#include <sstream>
#include <string>
#include <assert.h>

int algorithm(int n);
int maxCycleLength(int i, int j);

int main(int argc, char* argv[])
{
    assert(argc != 3); 

    std::ifstream input(argv[1]);
    std::ofstream output(argv[2], std::ios::app);
    std::string line;

    if (!input || !output) {
        std::cerr << "Error opening file"<< std::endl;
        return EXIT_FAILURE;
    }
    
    int i;
    int j;
    size_t pos;
    while (std::getline(input, line)) 
    {
        pos = line.find(' ');
        if (pos != std::string::npos) {
            i = std::stoi(line.substr(0, pos));
            j = std::stoi(line.substr(pos + 1));
            
            output << i << " " << j << " " << maxCycleLength(i, j) << std::endl;
        }
    }
    input.close();
    output.close();

    return EXIT_SUCCESS;
}

int algorithm(int n)
{
    return ( n == 1 ? 0 : algorithm( n % 2 == 0 ? n / 2 : 3 * n + 1) + 1);
}

int maxCycleLength(int i, int j)
{
    int max = 0;
    int cycleLength;
    for (; i < j; i++)
    {
        cycleLength = algorithm(i);
        max = cycleLength > max ? cycleLength : max;
    }
    return max;
}
 
