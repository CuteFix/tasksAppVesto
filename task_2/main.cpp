#include "stdafx.h"
#include <stdlib.h>
#include <stdio.h>
#include <iostream>
#include <time.h>

using namespace std;

#define move_types 8

int possible_moves[move_types][2] = {
	{ -1, -2 },{ -2, -1 },{ -2,  1 },{ 1, -2 },
	{ -1,  2 },{ 2, -1 },{ 1,  2 },{ 2,  1 }
};

int **global;
int size_xy;
int max_moves, back_ret;

/*Функция проверяет может ли быть сделан ход на клетку с координатами x,y*/
int move_possible(int x, int y) {
	return x >= 0 && y >= 0 && x < size_xy && global[x][y] == 0;
}

int find_path(int cur_x, int cur_y, int move_num) {
	int next_x = 0, next_y = 0;
	global[cur_x][cur_y] = move_num;

	if (move_num > max_moves) 
		return 1;

	for (int i = 0; i < move_types; i++) {
		next_x = cur_x + possible_moves[i][0];
		next_y = cur_y + possible_moves[i][1];
		if (move_possible(next_x, next_y) && find_path(next_x, next_y, move_num + 1))
			return 1;
	}

	global[cur_x][cur_y] = 0;
	back_ret++;
	move_num++;
	return 0;
}
/*главная функция*/
int main() {
	setlocale(LC_ALL, "");

	int i, nrows, ncols, sy, sx, **desc = NULL;
	//вводим данные x и y
	cout << "Enter board size (from 5 to 8) :"; cin >> size_xy;
	if (size_xy > 8 || size_xy < 5) {
		cout << "Wrong size" << endl;
		system("pause");
		return 0;
	}
	//проверяем размерность
	cout << "Enter \"X\"\t"; cin >> sx;
	cout << "Enter \"Y\"\t"; cin >> sy;

	if (sx > size_xy) {
		cout << "Wrong position" << endl;
		system("pause");
		return 0;
	}
	//инициализируем увзвтель и выделяем память
	desc = (int **)malloc(sizeof(int) * size_xy);
	for (i = 0; i < size_xy; i++)
		desc[i] = (int *)malloc(sizeof(int) * size_xy);
	//инициализируем другие переменные
	back_ret = 0;
	global = desc;
	max_moves = size_xy * size_xy - 1;
	//обнуление массива
	for (int i = 0; i < size_xy; i++) {
		for (int j = 0; j < size_xy; j++)
			global[i][j] = 0;
	}
	//поиск решения
	if (find_path(sx, sy, 1)) {
		for (int i = 0; i < size_xy; i++) {
			cout << endl;
			for (int j = 0; j < size_xy; j++)
				cout << global[i][j] << "\t";
			cout << endl;
		}
	}
	else cout << "Without solution" << endl;
	system("pause");
}