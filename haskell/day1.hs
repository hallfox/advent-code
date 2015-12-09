module Day1 where

santa :: Char -> Int
santa x
  | x == '(' = 1
  | x == ')' = -1
  | otherwise = 0

part1 :: [Int] -> Int
part1 = sum

part2 :: [Int] -> Int
part2 xs = fst . head . dropWhile notInBasement $ zip [0..] (scanl (+) 0 xs)
        where notInBasement = (/= (-1)) . snd

main :: IO ()
main = do
  parens <- getLine
  let directions = map santa parens
  putStr "Part 1: "
  print . part1 $ directions
  putStr "Part 2: "
  print . part2 $ directions
