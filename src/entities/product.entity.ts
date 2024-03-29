// src/entities/product.entity.ts
import { Entity, PrimaryGeneratedColumn, Column } from 'typeorm';

@Entity()
export class Product {
  @PrimaryGeneratedColumn()
  id: number; // 产品唯一标识符

  @Column({ length: 255 })
  name: string; // 产品名称

  @Column('text')
  description: string; // 产品描述

  @Column('int')
  pointsCost: number; // 换购产品所需的积分

  @Column('int')
  stock: number; // 产品库存数量

  @Column({ nullable: true })
  image: string; // 产品图片路径

  // 构造函数，用于创建Product实例时初始化属性
  constructor(
    name: string,
    description: string,
    pointsCost: number,
    stock: number,
    image?: string,
  ) {
    this.name = name;
    this.description = description;
    this.pointsCost = pointsCost;
    this.stock = stock;
    if (image) {
      this.image = image;
    }
  }
}
