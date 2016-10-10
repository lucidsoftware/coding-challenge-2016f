#include <iostream>
#include <utility>
#include <vector>

enum GridEntry
{
    EMPTY, UP, LEFT, RIGHT, DOWN
};

struct GridSolution
{
    bool onLoop;
    int setId;
};

int main()
{
    int nRows = 0, nCols = 0;
    std::cin >> nRows >> nCols;

    if (nRows < 1 || nCols < 1 || nRows > 10000 || nCols > 10000)
    {
        std::cerr << "ERROR: Invalid constraints on rows and columns " << nRows << ", " << nCols << std::endl;
        return 0;
    }

    std::vector< std::vector<GridEntry> > input;
    std::vector< std::vector<GridSolution> > output;
    input.reserve(nRows);
    output.reserve(nRows);
    for (int i = 0; i < nRows; i++)
    {
        input.push_back(std::vector<GridEntry>());
        input[i].reserve(nCols);

        output.push_back(std::vector<GridSolution>());
        output[i].reserve(nCols);

        for (int j = 0; j < nCols; j++)
        {
            char in = '\0';
            std::cin >> in;
            switch (in)
            {
                case '>': input[i].push_back(RIGHT); break;
                case '<': input[i].push_back(LEFT); break;
                case 'V': input[i].push_back(DOWN); break;
                case '^': input[i].push_back(UP); break;
                case '-': input[i].push_back(EMPTY); break;
                default: std::cerr << "Invalid character in grid: " << in << std::endl; return 0;
            }

            output[i].push_back({ false, 0 });
        }
    }

    //
    // Solution begins here
    //

    // Go through entire grid, starting at upper-left corner.
    // Follow the path as far as it goes, marking each spot as on the path
    //  with the current path ID
    // If at any time a spot is encountered with a path ID that isn't zero,
    //  if it is the current path ID, go around marking as "in loop" and keeping
    //   track of the loop length. Repeat until "in loop" is hit (the first)
    //  if it is not the current path ID, break
    // Increment the path ID and continue
    int pathId = 1; // Start at 1 - 0 is reserved for "no path"
    int maxLoopLength = 0;
    for (int r = 0; r < nRows; r++)
    {
        for (int c = 0; c < nCols; c++)
        {
            // Start on a path, continue as far as it goes
            int objectRow = r;
            int objectCol = c;
            
            // Follow path while on unique elements, setting path ID
            while
            (
                objectRow >= 0 && objectCol >= 0
                && objectRow < nRows && objectCol < nCols
                && output[objectRow][objectCol].setId == 0
            )
            {
                output[objectRow][objectCol].setId = pathId;
                switch (input[objectRow][objectCol])
                {
                    case UP: objectRow--; break;
                    case DOWN: objectRow++; break;
                    case LEFT: objectCol--; break;
                    case RIGHT: objectCol++;  break;
                    case EMPTY: break;
                    default: std::cerr << "Invalid state?? Not sure how... enum yeah." << std::endl; return 0;
                }
            }

            // At this point, we've encountered a non-zero path.
            // We only care if that path happens to be the one we're on currently
            int loopLength = 0;
            while
            (
                objectRow >= 0 && objectCol >= 0
                && objectRow < nRows && objectCol < nCols
                && output[objectRow][objectCol].setId == pathId
                && input[objectRow][objectCol] != EMPTY
                && output[objectRow][objectCol].onLoop == false
            )
            {
                output[objectRow][objectCol].onLoop = true;
                loopLength++;
                switch (input[objectRow][objectCol])
                {
                    case UP: objectRow--; break;
                    case DOWN: objectRow++; break;
                    case LEFT: objectCol--; break;
                    case RIGHT: objectCol++;  break;
                    case EMPTY: break;
                    default: std::cerr << "Invalid state?? Not sure how... enum yeah." << std::endl; return 0;
                }
            }

            // Update the maximum loop length
            if (loopLength > maxLoopLength)
            {
                maxLoopLength = loopLength;
            }

            // Finished with this path
            pathId++;
        }
    }

    std::cout << maxLoopLength << std::endl;
}