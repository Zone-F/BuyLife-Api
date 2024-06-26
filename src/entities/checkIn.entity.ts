// src/entity/checkIn.entity.ts
import {
  Entity,
  PrimaryGeneratedColumn,
  Column,
  // ManyToOne,
  CreateDateColumn,
  // JoinColumn,
} from 'typeorm';
// import { User } from './user.entity'; // 假设你有一个User实体
import { RepeatCycle } from '@/common/enums/repeatCycle.enum';
import { BaseEntity } from './base.entity';

@Entity({ name: 'check_ins' })
export class CheckIn extends BaseEntity {
  // @PrimaryGeneratedColumn()
  // id: number;

  // @ManyToOne(() => CheckInTask, (checkInTask) => checkInTask.id, {
  //   onDelete: 'CASCADE', // 或者其他你需要的onDelete option
  // })
  // @JoinColumn({ name: 'task_id' }) // 这里定义了外键列的名称
  // taskId: CheckInTask;

  @Column({ name: 'user_id' })
  userId: number;

  @Column({ name: 'task_id' })
  taskId: number;

  status: number;

  @CreateDateColumn({ type: 'timestamp' })
  checkInTime: Date;

  @Column({ type: 'timestamp', name: 'expiry_time' })
  expiryTime: Date;
}

@Entity({ name: 'check_in_tasks' })
export class CheckInTask extends BaseEntity {
  @PrimaryGeneratedColumn()
  id: number;

  // @ManyToOne(() => User, (User) => User.id, {
  //   onDelete: 'CASCADE',
  // })
  // @JoinColumn({ name: 'user_id' })
  // userId: User;

  @Column({ name: 'user_id' })
  userId: number;

  // @CreateDateColumn({ type: 'timestamp' })
  // @Column({ name: 'create_time', type: 'timestamp' })
  // createTime: Date;

  @Column({ type: 'text' })
  description: string;

  @Column({ name: 'point' })
  point: number;

  @Column({ type: 'enum', enum: RepeatCycle })
  repeatCycle: RepeatCycle;

  @Column({ type: 'date' })
  startDate: Date;

  status: number;
}
