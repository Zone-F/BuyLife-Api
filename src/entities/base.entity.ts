import { Exclude } from 'class-transformer';
import {
  Column,
  CreateDateColumn,
  PrimaryGeneratedColumn,
  UpdateDateColumn,
} from 'typeorm';

@Exclude()
export abstract class BaseEntity {
  @Column({
    type: 'bigint',
    length: 20,
  })
  @PrimaryGeneratedColumn()
  id: number;

  @CreateDateColumn({ type: 'timestamp', select: false, name: 'create_time' })
  createTime: Date;

  @UpdateDateColumn({ type: 'timestamp', select: false, name: 'update_time' })
  updateTime: Date;
}
