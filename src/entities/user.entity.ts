// src/entities/user.entity.ts
import { Entity, PrimaryGeneratedColumn, Column } from 'typeorm';
// import { CheckIn } from './check-in.entity';
// import { PurchaseHistory } from './purchase-history.entity';
import { BaseEntity } from './base.entity';

@Entity()
export class User extends BaseEntity {
  @PrimaryGeneratedColumn()
  id: number;

  @Column({ unique: true })
  username: string;

  @Column({ unique: true })
  email: string;

  @Column()
  password: string; // 这里的密码应当是加密后的字符串

  @Column('int', { default: 0 })
  points: number;

  // @OneToMany(() => CheckIn, (checkIn) => checkIn.user)
  // checkIns: CheckIn[];

  // @OneToMany(() => PurchaseHistory, (purchaseHistory) => purchaseHistory.user)
  // purchases: PurchaseHistory[];
}
