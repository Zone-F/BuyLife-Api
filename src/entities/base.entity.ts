/**
 * 数据库建表基类，实现了一些所有表都要有的字段
 */
import { Exclude } from 'class-transformer';
import { Column, PrimaryGeneratedColumn } from 'typeorm';

@Exclude()
export abstract class BaseEntity {
  
}