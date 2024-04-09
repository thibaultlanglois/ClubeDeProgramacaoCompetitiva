use std::{cmp, fs};

pub fn solution() {
    println!("");
    let raw: String = fs::read("./samples/minesweeper.txt")
        .expect("file not found")
        .iter()
        .map(|b| *b as char)
        .collect();
    let lines: Vec<&str> = raw.lines().collect();

    let mut fields = vec![];
    let mut lines = lines.iter();
    while let Some(line) = lines.next() {
        let values: Vec<_> = line.trim().split(' ').collect();
        let mut line_count: u32 = values[0]
            .trim()
            .parse()
            .expect(format!("invalid line count for field {}", fields.len()).as_str());
        let char_count: u32 = values[1]
            .trim()
            .parse()
            .expect(format!("invalid char count for field {}", fields.len()).as_str());
        if line_count == 0 && char_count == 0 {
            break;
        }
        let mut field = vec![];
        while line_count > 0 {
            if let Some(line) = lines.next() {
                field.push(line);
            }
            line_count -= 1;
        }
        fields.push(field);
    }

    for field in fields {
        let mut field: Vec<Vec<char>> = field.iter().map(|line| line.chars().collect()).collect();
        let lines = field.len();
        let chars = field[0].len();
        for i in 0..lines {
            for j in 0..chars {
                field[i][j] = get_neighbors(&field, i, j);
            }
        }
        for line in field {
            println!("{}", line.iter().collect::<String>());
        }
        println!("");
    }
}

fn get_neighbors(field: &Vec<Vec<char>>, i: usize, j: usize) -> char {
    if field[i][j] == '*' {
        return '*';
    }
    let mut count: u8 = '0' as u8;
    for i in cmp::max(0, i as i32 - 1)..=cmp::min(i as i32 + 1, field.len() as i32 - 1) {
        for j in cmp::max(0, j as i32 - 1)..=cmp::min(j as i32 + 1, field[0].len() as i32 - 1) {
            if field[i as usize][j as usize] == '*' {
                count += 1;
            }
        }
    }
    count as char
}
