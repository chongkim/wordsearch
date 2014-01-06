import System.Environment
import Data.String
import Data.List.Split

main = do
  args <- getArgs
  buf <- readFile $ args !! 0
  let gridStr : wordStr : [] = splitOn "\n\n" buf
  let grid = splitOn "\n" gridStr
  putStrLn $ show grid
  putStrLn show length $ grid
