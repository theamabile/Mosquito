package com.newlecture.mosquito.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.Random;

import com.newlecture.mosquito.canvas.StageCanvas;
import com.newlecture.mosquito.service.ImageLoader;

public class Mosquito extends Bug {

	private int deleteTimer = 60;

	private Random rand = new Random();
	// 모기 스탯변수
	private int power;

	// 생성자
	public Mosquito() {
		// this(100,100); //랜덤값으로 수정
		super();
		// 실제 전시 크기
		this.setWidth(60);
		this.setHeight(60);

		// 실제 파일에서의 이미지 크기 (ex: 모기가 가로로 10개인 이미지에서 모기 하나당 width가 320인거임)
		this.setImgWidth(320);
		this.setImgHeight(270);

		// 방향 전환 시 필요한 이미지 간격
		// (ex : imgDirection=270이면 왼쪽방향 이미지와 오른쪽 방향 이미지 위치 차이가 270인격)
		this.setImgDirection(270);

		setImg(ImageLoader.mosquito);
		this.setDirection((int) this.getImgHeight());
	}

	// //페인트 함수
	public void paint(Graphics g) {
		// 우리가 화면에 전시할 모기 크기 ex:60
		int w = (int) this.getWidth();
		int h = (int) this.getHeight();

		// 중심 좌표로 맞출거임
		int x1 = (int) this.getX() - w / 2;
		int y1 = (int) this.getY() - h / 2;
		int x2 = x1 + w; // + 60;
		int y2 = y1 + h;

		int walkTempo = getWalkTempo();
		int movIndex = getMovIndex();

		Image img = getImg();

		if (walkTempo == 0) {
			movIndex++;
			movIndex = movIndex % 5;
			walkTempo = 3;
		} else
			walkTempo--;

		int imgWidth = (int) getImgWidth();
		int imgHeight = (int) getImgHeight();

		int offsetX = movIndex * imgWidth;
		if (movIndex < 5)
			offsetX -= 1;

		if (true == isAttacked()) { // 공격 당했을 경우
			if (this.getCurrentDir() == 0) {			// 오른쪽
				g.drawImage(img, x1, y1, x2, y2, 
						imgWidth, imgHeight*2, imgWidth*2, imgHeight*3, StageCanvas.instance);		// 왼쪽 방향
			} else {		// 왼쪽
				g.drawImage(img, x1, y1, x2, y2, 
						0, imgHeight*2, imgWidth, imgHeight*3, StageCanvas.instance);				// 오른쪽 방향
			}
		} else { 					// 평상시
			if (this.getCurrentDir() != 2)// 살아 있을 때
				g.drawImage(img, x1, y1, x2, y2, 0 + offsetX, this.getDirection(), imgWidth + offsetX,
						this.getDirection() + imgHeight, StageCanvas.instance);
			else// 죽었을 때
				g.drawImage(img, x1, y1, x2, y2, 0 + 4 * imgWidth, 270 * 2, imgWidth + 4 * imgWidth,
						270 * 2 + imgHeight, StageCanvas.instance);
		}

		setWalkTempo(walkTempo);
		setMovIndex(movIndex);
	}

	// 공격함수
	public void attack() {
		int power = this.power;
		power = 1; // 변수 처리
	}

	public int getDeleteTimer() {
		return deleteTimer;
	}

	public void setDeleteTimer(int deleteTimer) {
		this.deleteTimer = deleteTimer;
	}

}
