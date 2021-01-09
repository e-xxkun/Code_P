"""
The DQN improvement: Prioritized Experience Replay (based on https://arxiv.org/abs/1511.05952)

View more on my tutorial page: https://morvanzhou.github.io/tutorials/

Using:
Tensorflow: 1.0
gym: 0.8.0
"""


from RL_brain import DQNPrioritizedReplay
import matplotlib.pyplot as plt
import tensorflow.compat.v1 as tf
import numpy as np
import pickle
import time
import copy
import Env

MEMORY_SIZE = 200000
# MEMORY_SIZE = 10000
VUE_NUM = 2
CUE_NUM = 2
P_NUM = 3
L_NUM = 3
SLOT_TOTAL = 10000

def train(RL):
    Iter_num = 5000
    total_steps = 0
    env = Env.Env(CUE_NUM, VUE_NUM, P_NUM, L_NUM)
    vehicles = []
    rewards = []
    reward_avgs = []
    for _ in range(VUE_NUM):
        vehicles.append(Env.V2V_Vehicle(np.random.randint(80,120), [30, 36], [40, 60], 0.01, 6400, 0.001))

    for i_episode in range(Iter_num):
        env_ = copy.deepcopy(env)
        vehs = copy.deepcopy(vehicles)

        state = env_.reset(vehs)
        for slot_cur in range(SLOT_TOTAL):

            action = RL.choose_action(state)
            state_, reward, end = env_.update_state(slot_cur, vehs, action)
            RL.store_transition(state, action, reward, state_)
            rewards.append(reward)

            if total_steps > MEMORY_SIZE:
                cost = RL.learn()
                
                reward_avg = np.mean(rewards)
                reward_avgs.append(reward_avg)

                print('--cost:', cost)
                print('--reward:', reward_avg)

                rewards.clear()

            if end:
                print('episode ', i_episode, ' finished')
                break
            
            for vehicle in vehs:
                vehicle.run(slot_cur + 1)

            state = state_
            total_steps += 1
    return {'cost':RL.cost_his, 'reward_avg':reward_avgs}

def main():
    sess = tf.Session()
    with tf.variable_scope('DQN_with_prioritized_replay'):
        RL_prio = DQNPrioritizedReplay(
            n_actions=VUE_NUM * CUE_NUM * P_NUM * L_NUM, n_features=VUE_NUM*5+CUE_NUM, memory_size=MEMORY_SIZE,
            e_greedy_increment=0.00005, sess=sess, prioritized=True
        )
    sess.run(tf.global_variables_initializer())

    his_prio = train(RL_prio)

    f = open('./Main/data/cost'+time.strftime('_%m_%d_%H_%M_%S'),'wb')
    pickle.dump(his_prio,f)

    # compare based on first success
    # plt.plot(his_prio[0, :], his_prio[1, :] - his_prio[1, 0], c='r', label='DQN with prioritized replay')
    # plt.legend(loc='best')
    # plt.ylabel('total training time')
    # plt.xlabel('episode')
    # plt.grid()
    # plt.show()

if __name__ == "__main__":
    main()



